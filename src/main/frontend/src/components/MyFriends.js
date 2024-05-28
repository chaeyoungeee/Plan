import { Row } from 'react-bootstrap';
import { FiPlus } from 'react-icons/fi';
import { Friend } from './Friend';
import { useSelector } from 'react-redux';

export const MyFriends = () => {
    const friends = useSelector(state=>state.user.friends)
    return (
        <div id="myfriends">
            <div className="title">
                <div>친구</div>
                <div className="my-btn add-btn">
                    <FiPlus />
                </div>
            </div>
            <div className='friends'>
                {friends.map((friend) => (
                    <Friend key={friend.id} friend={friend} />
                ))}
            </div>
        </div>
    );
};
