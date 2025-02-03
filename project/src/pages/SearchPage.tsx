import {useEffect, useState} from "react";

interface Medication {
    id: number;
    name: string;
    injectionType: string;
    activeSubstance: number;
}

function SearchPage() {
    const [medications, setMedications] = useState<Medication[]>([]);
    const [search, setSearch] = useState<string>("");
    const [selectedMedication, setSelectedMedication] = useState<Medication | null>(null);
    const [error, setError] = useState<string | null>(null);

    useEffect(() => {
        fetchMedications().catch((error) => {
            console.error(error);
            setError("Failed to fetch medications.");
        });
    }, []);

    const fetchMedications = async () => {
        try {
            const response = await fetch("/api/search/medication/all");
            if (!response.ok) throw new Error("Failed to fetch Medications");
            const data: Medication[] = await response.json();
            setMedications(data);
        } catch (error) {
            setError("Failed to fetch Medications");
        }
    };

    const handleSearch = async () => {
        if (!search.trim()) return;
        try {
            const response = await
                fetch(`/api/search/medication/name/${encodeURIComponent(search.trim())}`);
            if (!response.ok) throw new Error("Failed to fetch Medications");
            const data: Medication[] = await response.json();
            setMedications(data);
        } catch (error) {
            setError("Failed to fetch Medications");
            setMedications([]);
        }
    };

    const handleSelect = async (id: number) => {
        try {
            const response = await fetch(`/api/search/medication/id/${id}`);
            if (!response.ok) throw new Error("Failed to fetch Medications");
            const data: Medication = await response.json();
            setSelectedMedication(data);
        } catch (error: unknown) {
            setError("Failed to fetch Medications");

            setSelectedMedication(null);
        }
    };

    return (
        <div className="search-page">
            <div className="search-container">
                <input type="text" value={search} onChange={(e) =>
                    setSearch(e.target.value)}
                       placeholder="search by name" className="search-input"/>
                <button onClick={handleSearch} className="search-button">Поиск</button>
            </div>
            {error && <p className="search-error">{error}</p>}
            <ul className="search-list">
                {medications.length > 0 ? (medications.map(medication => (
                        <li key={medication.id} className="search-item" onClick={() =>
                            handleSelect(medication.id)}>
                            {medication.name} - {medication.injectionType}
                        </li>
                    ))
                ) : (
                    <p className="search-no-found">No medications found</p>
                )}
            </ul>
            {selectedMedication && (
                <div className="search-selected-list">
                    <h2 className="search-selected-text">{selectedMedication.name}</h2>
                    <p>ID: {selectedMedication.id}</p>
                    <p> тип введения: {selectedMedication.injectionType}</p>
                    <p>Действующее вещество: {selectedMedication.activeSubstance}</p>
                </div>
            )}
        </div>
    );
}

export default SearchPage;