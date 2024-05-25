import { MdPerson } from 'react-icons/md';
import { useSelector } from 'react-redux';

export const Profile = () => {
    const user = useSelector((state) => state.user);

    return (
        <div id="profile">
            <div className="photo">
                <MdPerson className="icon" color="white" />
            </div>
            <div className="nickname">{user.nickname}</div>
        </div>
    );
};
