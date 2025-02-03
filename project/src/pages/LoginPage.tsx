import {useState} from "react";
import {useNavigate} from "react-router-dom";

function LoginPage() {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const navigate = useNavigate();

    const handleSubmit = async (e: React.FormEvent) => {
        e.preventDefault();

        const formData = new URLSearchParams();
        formData.append("username", username);
        formData.append("password", password);
        try {
            const response = await fetch("http://localhost:8080/api/login", {
                method: "POST",
                headers: {"Content-Type": "application/x-www-form-urlencoded"},
                body: formData.toString(),
                redirect: 'follow'
            });

            if (response.ok) {
                const data = await response.json();
                const redirectUrl = data.redirectUrl || '/home';
                localStorage.setItem("token", data.token);
                console.log('Успешный вход: ', data);

                navigate(redirectUrl, {replace: true})
            } else {
                console.error('Ошибка входа: ', response.statusText);
            }
        } catch
            (error) {
            console.error('Ошибка сети: ', error);
        }
    };
    return (
        <form onSubmit={handleSubmit}>
            <div>
                <label htmlFor="username">Логин</label>
                <input type="text" id="username" value={username} placeholder={"Логин"}
                       onChange={(e) => setUsername(e.target.value)} />
            </div>
            <div>
                <label htmlFor="password">Пароль</label>
                <input type="password" id="password" value={password} onChange
                    ={(e) => setPassword(e.target.value)} />
            </div>
            <button type="submit">LOGIN</button>
        </form>
    )
}

export default LoginPage;