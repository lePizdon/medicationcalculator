import {useNavigate} from "react-router-dom";

function MedicationDetail() {
    const navigate = useNavigate();
    return (
        <div className="detail-module">
            <h2 className="detail-module-header">Детальная информация о лекарстве</h2>
            <button onClick={() => navigate(-1)} className="detail-module__btn">Назад</button>
        </div>
    );
}

export default MedicationDetail;