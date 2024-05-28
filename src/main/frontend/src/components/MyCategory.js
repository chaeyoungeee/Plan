import { Col } from "react-bootstrap";
import { CategoryV1 } from "./Category";
import { FiPlus } from 'react-icons/fi';
import { useSelector } from "react-redux";
import { useEffect } from "react";

export const MyCategory = () => {
    
    const categories = useSelector((state)=> state.user.categories)

    useEffect(()=>{
        console.log(categories)
    })

    return (
        <div id="mycategory">
            <div className="title">
                <div>카테고리</div>
                <div className="my-btn add-btn">
                    <FiPlus />
                </div>
            </div>
            {categories.map((category) => (
                <CategoryV1 key={category.id} category={category} />
            ))}
        </div>
    );
}