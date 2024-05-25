import { Row } from 'react-bootstrap';
import { FiPlus } from 'react-icons/fi';
import { Friends } from './Friends';

export const MyFriends = () => {
    const frineds = [
        {
            id: 1,
            nickname: '채영',
        },
        {
            id: 2,
            nickname: '지연',
        },
        {
            id: 3,
            nickname: '유빈',
        },
    ];
    return (
        <div id="myfriends">
            <div className="title">
                <div>친구</div>
                <div className="my-btn add-btn">
                    <FiPlus />
                </div>
            </div>
            <div className='friends'>
                {frineds.map((friend) => (
                    <Friends key={friend.id} friend={friend} />
                ))}
            </div>
        </div>
    );
};
