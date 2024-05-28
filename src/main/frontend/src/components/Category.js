import axios from 'axios';
import { AiOutlineDelete } from 'react-icons/ai';
import { useDispatch } from 'react-redux';
import { deleteCategory } from '../store/user';
import { useEffect } from 'react';

export const CategoryV1 = ({ category }) => {
    const dispatch = useDispatch();

    const handleClick = async () => {
        console.log(category)
        await axios
        .delete(`/category/${category.categoryId}`
        ).then((res)=>{
            dispatch(deleteCategory(category.categoryId))
           console.log(res.data) 
        }) 
    }
    useEffect(()=>{
        console.log(category)
    })
    return (
        <div id="category" style={{ backgroundColor: category.color }}>
            <div>{category.name}</div>
            <div className="my-btn delete-btn" onClick={handleClick}>
                <AiOutlineDelete />
            </div>
        </div>
    );
};


export const CategoryV2 = ({ category, setCategory, setShowModal }) => {
    const handleClick = () => {
        setCategory(category)
        setShowModal(true)
    }
    useEffect(() => {
        console.log(category);
    });
    return (
        <div onClick={handleClick} style={{ backgroundColor: category.color }} className="category" id="category-v2">
            <div>{category.name}</div>
        </div>
    );
};