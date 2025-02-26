import {useNavigate, useParams} from "react-router-dom";
import {useEffect, useState} from "react";

interface Dosage {
    id: number;
    startValue: number;
    endValue: number;
    animalType: string;
    medicationId: number;
    medicationName: string;
    medicationInjectionType: string;
}

function DosageDetail() {
    const { id } = useParams<{ id: string }>();
    const navigate = useNavigate();
    const[dosage, setDosage] = useState<Dosage | null>(null)
    useEffect(() => {
        fetch(`/api/search/dosage/id/${id}`)
            .then((res) => res.json())
            .then((data) => setDosage(data))
            .catch((error) => console.log(error));
    }, [id]);
    return (
        <div className="detail-module">
            <h2 className="detail-module-header">Детальная информация о дозировке</h2>
            <h3 className="detaul-module-header">{dosage ? dosage.medicationName : "Дозировка загружается"}</h3>
            <p>{dosage ? dosage.animalType : "Загрузка..."}</p>
            <button onClick={() => navigate(-1)} className="detail-module__btn">Назад</button>
        </div>
    );
}

export default DosageDetail;