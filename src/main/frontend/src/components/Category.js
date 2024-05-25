import axios from 'axios';
import { AiOutlineDelete } from 'react-icons/ai';

export const Category = ({ category }) => {
    const handleClick = async () => {
        console.log(category)
        await axios
        .delete(`/category/${category.categoryId}`
        ).then((res)=>{
           console.log(res.data) 
        }) 
    }
    console.log(category)
    return (
        <div id="category" style={{ backgroundColor: category.color }}>
            <div>{category.name}</div>
            <div className="my-btn delete-btn" onClick={handleClick}>
                <AiOutlineDelete />
            </div>
        </div>
    );
};
