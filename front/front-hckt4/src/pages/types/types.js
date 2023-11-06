import { useEffect, useState } from "react"
import { fetchTypes } from "../../api/dataService"
import 'devextreme/data/odata/store';
import DataGrid, { Column } from 'devextreme-react/data-grid';

export default function Type() {
    // Se declara un estado llamado 'groupsWithPersonCount' utilizando useState.
    const [typeWithPersonCount, setTypeWithPersonCount] = useState([]);

    // Utiliza useEffect para realizar la solicitud a la API cuando el componente se monta.
    useEffect(() => {
        fetchTypes()
            .then((response) => {
                // Extrae los datos de respuesta de la solicitud.
                const groupsData = response.data;

                // Registra los datos de grupos en la consola.
                console.log(groupsData);

                // Modifica el estado 'groupsWithPersonCount' transformando los datos de grupos.
                setTypeWithPersonCount(groupsData.map(group => ({
                    id: group.id,
                    name: group.name
                })))
            })
            .catch((error) => {
                console.log(error);
            })
    }, []); // El [] como segundo argumento asegura que useEffect se ejecute solo una vez al montar el componente.

    return (
        <DataGrid

            className={'dx-card wide-card'}
            dataSource={typeWithPersonCount}
            showBorders={false}
        >
            <Column dataField='id' width={50} />
            <Column dataField='name' />

        </DataGrid>

    )
}