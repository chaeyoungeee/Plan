import 'bootstrap/dist/css/bootstrap.min.css';
import './styles/main.scss';
import MainNav from './components/MainNav';
import Calendar from './pages/Calendar';
import { useSelector } from 'react-redux';
import { Route, Routes } from 'react-router-dom';
import { Join } from './pages/Join';
import { useEffect } from 'react';
import { Mypage } from './pages/Mypage';
import { Login } from './pages/Login';
import { AddPlanModal } from './components/modals/AddPlanModal';

function App() {
    const user = useSelector((state) => state.user);

    let plans;

    useEffect(() => {
        console.log(user);
        if (user != null) {
            plans = user.plans;
        }
    }, [user]);

    return (
        <div className="App">
            <MainNav></MainNav>

            <Routes>
                <Route path="/" element={<>

                </>} />
                <Route path="/calendar" element={<Calendar />} />
                <Route path="/mypage" element={user.userId != null ? <Mypage /> : <Login />} />
                <Route path="/signup" element={<Join />} />
            </Routes>
        </div>
    );
}

export default App;
