import {useEffect, useState} from "react";
import {Link} from "react-router-dom";
import {InjectionTypeMap} from "../components/InjectionTypeMap.tsx";
import {capitalize} from "../components/utils.tsx";
import "../assets/styles/searchpage.css"

interface Medication {
    id: number;
    name: string;
    injectionType: string;
    activeDosage: number
}

interface Dosage {
    id: number;
    startValue: number;
    endValue: number;
    animalType: string;
    medicationId: number;
    medicationName: string;
    medicationInjectionType: string;
}

function SearchPage() {
    const [activeMedicationTab, setActiveMedicationTab] =
        useState<"Лекарства" | null>(null);
    const [activeDosageTab, setActiveDosageTab] = useState<"Дозировки" | null>(null);
    const [medications, setMedications] = useState<Medication[]>([]);
    const [dosages, setDosages] = useState<Dosage[]>([]);
    const [expandedMedicationGroups, setMedicationExpandedGroups] = useState<Record<string, boolean>>({});
    const [expandedDosageGroups, setDosageExpandedGroups] = useState<Record<string, boolean>>({});

    useEffect(() => {
        fetch("/api/search/medication/all")
            .then((res) => res.json())
            .then((data) => setMedications(data))
            .catch((error) => console.log(error));
        fetch("/api/search/dosage/all")
            .then((res) => res.json())
            .then((data) => setDosages(data))
            .catch(error => console.log(error));
    }, []);

    const toggleMedicationGroup = (medicationName: string) => {
        setMedicationExpandedGroups(prev => ({
            ...prev,
            [medicationName]: !prev[medicationName],
        }))
    }

    const toggleDosageGroup = (medicationName: string) => {
        setDosageExpandedGroups(prev => ({
            ...prev,
            [medicationName]: !prev[medicationName],
        }))
    }

    return (
        <div className="search-page">
            <h1 className="search-page-header">Страница поиска</h1>


            <div className="search-container">
                <div className="search-column-left">
                    <button onClick={() => setActiveMedicationTab(activeMedicationTab === "Лекарства" ? null : "Лекарства")}
                            className="search-tab-button">
                        Лекарства
                    </button>
                    <div className="search-tab-content-module">
                        {activeMedicationTab === "Лекарства" && (
                            <ul>
                                {Object.entries(
                                    medications.reduce((acc, medication) => {
                                        if (!acc[medication.name]) {
                                            acc[medication.name] = [];
                                        }
                                        acc[medication.name].push(medication);
                                        return acc;
                                    }, {} as Record<string, Medication[]>)
                                ).map(([medName, medicationList]) => (
                                    <li key={medName} className="search-tab-content-group">
                                        <h3 className="search-tab-content-group-title"
                                            onClick={() => toggleMedicationGroup(medName)}>{capitalize(medName)}</h3>
                                        {expandedMedicationGroups[medName] && (
                                            <ul>
                                                {medicationList.map((medication: Medication) => (
                                                    <li key={medication.id} className="search-tab-content-item">
                                                        <Link to={`/medication/${medication.id}`}
                                                              className="search-tab-link">
                                                            {InjectionTypeMap[medication.injectionType]}
                                                        </Link>
                                                    </li>
                                                ))}
                                            </ul>
                                        )}
                                    </li>
                                ))}
                            </ul>
                        )}
                    </div>
                </div>
                <div className="search-column-right">
                    <button onClick={() => setActiveDosageTab(activeDosageTab === "Дозировки" ? null : "Дозировки")}
                            className="search-tab-button">
                        Дозировки
                    </button>
                    {activeDosageTab === "Дозировки" && (
                        <ul>
                            {Object.entries(
                                dosages.reduce((acc, dosage) => {
                                    if (!acc[dosage.medicationName]) {
                                        acc[dosage.medicationName] = [];
                                    }
                                    acc[dosage.medicationName].push(dosage);
                                    return acc;
                                }, {} as Record<string, Dosage[]>),
                            ).map(([medicationName, dosageList]) => (
                                <li key={medicationName} className="search-tab-content-group">
                                    <h3 className="search-tab-content-group-title"
                                        onClick={() => toggleDosageGroup(medicationName)}>{capitalize(medicationName)}</h3>
                                    {expandedDosageGroups[medicationName] && (
                                        <ul>
                                            {dosageList.map((dosage: Dosage) => (
                                                <li key={dosage.id} className="search-tab-content-item">
                                                    <Link to={`/dosage/${dosage.id}`} className="search-tab-link">
                                                        {capitalize(dosage.animalType)}, {InjectionTypeMap[dosage.medicationInjectionType]}
                                                    </Link>
                                                </li>
                                            ))}
                                        </ul>
                                    )}
                                </li>
                            ))}
                        </ul>
                    )}
                </div>
            </div>
        </div>
    )
}

export default SearchPage;
