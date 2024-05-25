import { Col, Row } from "react-bootstrap";
import { Profile } from "../components/Profile";
import { MyCategory } from "../components/MyCategory";
import { MyFriends } from "../components/MyFriends";

export const Mypage = () => {
    return (
        <div id="mypage">
            <Profile />
            <div className="my">
                <MyCategory />
                <MyFriends />
                {/* <MyFriends md={6}/> */}
            </div>
        </div>
    );
}