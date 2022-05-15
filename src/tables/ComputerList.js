import { useState } from "react";
import "./List.css"

const tableHeaders = ["Id", "Procesor", "Karta graficzna", "Płyta główna", "Użytkownicy", "Model", "System operacyjny", "Pamięć RAM", "Długość", "Szerokość", "Wysokość", "Pamięć", "Moc", "Cena"];
const headerMapping = {
    "Id": "id",
    "Procesor": "processorId",
    "Karta graficzna": "graphicsCardId",
    "Płyta główna": "motherboardId",
    "Użytkownicy": "userIds",
    "Model": "model",
    "System operacyjny": "operatingSystem",
    "Pamięć RAM": "RAM",
    "Długość": "length",
    "Szerokość": "width",
    "Wysokość": "height",
    "Pamięć": "memory",
    "Moc": "wattage",
    "Cena": "price"
};

async function getIncome(id) {
    return await fetch("http://localhost:8080/api/computer/" + id);
}

function filterComputers(computers, filters) {
    let newComputers = computers.map(x => x);
    for (const [key, value] of Object.entries(filters)) {
        newComputers = newComputers.filter(x => JSON.stringify(x[headerMapping[key]]).includes(value));
    }
    return newComputers;
}

async function generateXML(ids) {
    const computerResponse = await fetch("http://localhost:8080/api/computer/fullInfo/" + ids.join(','));
    const computers = await computerResponse.json();
    let xml = '<?xml version="1.0" encoding="UTF-8"?>\n';
    xml += '<computers>\n';
    for (const computer of computers) {
        xml += '<computer>\n';
        for (const [key, value] of Object.entries(computer)) {
            switch (key) {
                case "processor":
                case "graphicsCard":
                case "motherboard":
                    xml += `\t<${key}>\n`;
                    for (const [key1, value1] of Object.entries(computer[key])) {
                        xml += `\t\t<${key1}>${value1}</${key1}>\n`;
                    }
                    xml += `\t</${key}>\n`;
                    break;
                case "users":
                    xml += '\t<users>\n';
                    for (const user of computer.users) {
                        xml += '\t\t<user>\n';
                        for (const [key1, value1] of Object.entries(user)) {
                            if (key1 === "birthDate") {
                                xml += `\t\t\t<${key1}>${value1.slice(0, 10)}</${key1}>\n`;
                            } else {
                                xml += `\t\t\t<${key1}>${value1}</${key1}>\n`;
                            }
                        }
                        xml += '\t\t</user>\n';
                    }
                    xml += '\t</users>\n';
                    break;
                default:
                    xml += `\t<${key}>${value}</${key}>\n`;
            }
        }
        xml += '</computer>\n';
    }
    xml += '</computers>\n';
    return xml;
}

async function download(computers) {
    const element = document.createElement('a');
    const xml = await generateXML(computers.map(x => x.id));
    element.setAttribute('href', 'data:text/plain;charset=utf-8,' + encodeURIComponent(xml));
    element.setAttribute('download', "komputery_" + Date.now() + ".xml");

    element.style.display = 'none';
    document.body.appendChild(element);

    element.click();

    document.body.removeChild(element);
}

export function ComputerList() {
    const [computers, setComputers] = useState();
    const [filteredComputers, setFilteredComputers] = useState();
    const [filters, setFilters] = useState({});
    const [prices, setPrices] = useState({})

    if (!computers) {
        fetch("http://localhost:8080/api/computer").then(response => response.json()).then(body => {
            const tmp = body.map(x => { return { ...x, userIds: x.userIds.join(", ") } });
            setComputers(tmp);
            setFilteredComputers(tmp);
        });
    }

    return (
        <main style={{ display: "flex", flexDirection: "column", alignItems: "center", gap: "20px" }}>
            <button onClick={() => download(filteredComputers)}>Pobierz raport</button>
            <div style={{ display: "flex", flexWrap: "wrap", gap: "10px" }}>
                {
                    tableHeaders.map(header =>
                        <div key={header}>
                            <label htmlFor={header} style={{ marginRight: "10px" }}>{header}</label>
                            <input id={header} onChange={value => {
                                const newFilters = { ...filters };
                                newFilters[header] = value.target.value;
                                setFilters(newFilters);
                                setFilteredComputers(filterComputers(computers, newFilters));
                            }}></input>
                        </div>
                    )
                }
            </div>
            {
                filteredComputers?.length > 0 ?
                    <table>
                        <thead>
                            <tr>
                                {
                                    tableHeaders.map(header => <th key={header}>{header}</th>)
                                }
                                <th>Zysk</th>
                            </tr>
                        </thead>
                        <tbody>
                            {
                                filteredComputers.map(computer =>
                                    <tr key={computer.id}>
                                        {Object.keys(computer).map(header =>
                                            <td key={header}>{computer[header]}</td>
                                        )}
                                        <td>
                                            {
                                                prices[computer.id] ??
                                                <button onClick={() => getIncome(computer.id).then(response => response.json()).then(body => {
                                                    const newPrices = { ...prices };
                                                    newPrices[computer.id] = body;
                                                    setPrices(newPrices);
                                                })}>Oblicz</button>
                                            }
                                        </td>
                                    </tr>)
                            }
                        </tbody>
                    </table >
                    :
                    <p>Brak wyników.</p>
            }
        </main >
    );
}