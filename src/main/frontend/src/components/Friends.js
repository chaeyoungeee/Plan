import { Col } from "react-bootstrap";
import { MdPerson } from "react-icons/md";

export const Friends = ({friend}) => {
    return (
        <div id="friend" className="pl-1" md={4}>
            <div className="profile">
                <div className="photo">
                <MdPerson className="icon" color="white" />
                </div>
                <div className="nickname">{friend.nickname}</div>
            </div>
        </div>
    );
}