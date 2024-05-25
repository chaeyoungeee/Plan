import 'bootstrap/dist/css/bootstrap.min.css';
import { AiFillHome, AiFillCalendar, AiFillSmile } from 'react-icons/ai';
import { useLocation, useNavigate } from 'react-router-dom';
import { useState } from 'react';

function MainNav() {
    const navigate = useNavigate();


    return (
        <div id="navbar">
            <div
                className="nav"
                onClick={() => {
                    navigate('/');
                }}
            >
                <AiFillHome size={20} />
            </div>
            <div
                className="nav"
                onClick={() => {
                    navigate('/calendar');
                }}
            >
                <AiFillCalendar size={20} />
            </div>
            <div
                className="nav"
                onClick={() => {
                    navigate('/mypage');
                }}
            >
                <AiFillSmile size={20} />
            </div>
        </div>
    );
}

export default MainNav;
