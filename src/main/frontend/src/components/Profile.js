import { MdPerson } from 'react-icons/md';
import { IoLogOutOutline } from 'react-icons/io5';
import { useDispatch, useSelector } from 'react-redux';
import { resetUser } from '../store/user';
import { redirect } from 'react-router-dom';

export const Profile = () => {
    const user = useSelector((state) => state.user);
    const dispatch = useDispatch();

    const handleLogout = () => {
        dispatch(resetUser())
        redirect("/mypage")
    }

    return (
        <div className="profile-box d-flex align-items-center justify-content-between">
            <div id='profile'>
                <div className="photo">
                    <MdPerson className="icon" color="white" />
                </div>
                <div className="nickname">{user.nickname}</div>
            </div>

            <div className="logout-btn" onClick={handleLogout}>
                <IoLogOutOutline size={15}/>
            </div>
        </div>
    );
};
