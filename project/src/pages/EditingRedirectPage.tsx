import {useState} from "react";
import axios from "axios";

interface Medication {
    id: number;
    name: string;
    injectionType: string;
    activeSubstance: string;
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

const EditingRedirectPage: React.FC = () => {
    const [medications, setMedications] =
        useState<Medication[]>([]);
    const [medication, setMedication] =
        useState<Medication | null>(null);
    const [dosages, setDosages] =
        useState<Dosage[]>([])
    const [dosage, setDosage] =
        useState<Dosage | null>(null);
    const [dosageFormValue, setFormValues] =
        useState<{
            medicationName?: string;
            injectionType?: string;
            animalType?: string;
            medicationId?: number;
            dosageId?: number
        }>({})
    const [medicationFormValue, setMedicationFormValue] =
        useState<{
            name?: string;
            injectionType?: string
        }>({})

    const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        const {name, value} = e.target;
        setFormValues((prev) => ({
            ...prev,
            [name]: value.trim() === '' ? undefined : value,
        }));
    }

    const handleDosageSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
        e.preventDefault();

        const params: Record<string, string | number> = Object.entries(dosageFormValue)
            .filter(([_, value]) => value !== undefined)
            .reduce((acc, [key, value]) =>
                ({...acc, [key]: value}), {})
        if (Object.keys(params).length === 0) {
            fetch("api/search/dosage/all")
                .then(res => res.json()
                )
                .then(dosages => setDosages(dosages))
                .catch(error => console.log(error));
        }
        try {
            const response = await axios.get('/api/search/dosage/params', {params});
            setDosages(response.data)
        } catch (error) {
            console.log(error);
        }
    }

    const handleMedicationSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
        e.preventDefault();

        const params: Record<string, string | number> = Object.entries(medicationFormValue)
            .filter(([_, value]) => value !== undefined)
            .reduce((acc, [key, value]) => ({...acc, [key]: value}), {})
        if (Object.keys(params).length === 0) {
            fetch("api/search/medication/all")
                .then(res => res.json())
                .then(medications => setMedications(medications))
                .catch(error => console.log(error));
        }
        try {
            const response = await axios.get('api/search/medication/params',
                {params});
            setMedications(response.data)
        } catch (error) {
            console.log(error);
        }
    }
    return (
        <h2>PLACEHOLDER_РЕДАКТИРОВАНИЕ ЛЕКАРСТВ_PLACEHOLDER</h2>
    )
}

export default EditingRedirectPage;