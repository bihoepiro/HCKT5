import { useEffect, useState } from "react"
import { fetchGroups } from "../../api/dataService"
import 'devextreme/data/odata/store';
import DataGrid, { Column } from 'devextreme-react/data-grid';

export default function Group() {
    // Se declara un estado llamado 'groupsWithPersonCount' utilizando useState.
    const [groupsWithPersonCount, setGroupsWithPersonCount] = useState([]);

    // Utiliza useEffect para realizar la solicitud a la API cuando el componente se monta.
    useEffect(() => {
        fetchGroups()
            .then((response) => {
                // Extrae los datos de respuesta de la solicitud.
                const groupsData = response.data;

                // Registra los datos de grupos en la consola.
                console.log(groupsData);

                // Modifica el estado 'groupsWithPersonCount' transformando los datos de grupos.
                setGroupsWithPersonCount(groupsData.map(group => ({
                    id: group.id,
                    name: group.name,
                    personCount: group.members ? group.members.length : 0,
                    types: group.nameTypes
                })))
            })
            .catch((error) => {
                console.log(error);
            })
    }, []); // El [] como segundo argumento asegura que useEffect se ejecute solo una vez al montar el componente.

    return (
        <DataGrid

            className={'dx-card wide-card'}
            dataSource={groupsWithPersonCount}
            showBorders={false}
        >
            <Column dataField='id' width={50} />
            <Column dataField='name' />
            <Column dataField='personCount' caption="Number of Person"/>
            <Column dataField='types' caption="Types"/>

        </DataGrid>

    )
}