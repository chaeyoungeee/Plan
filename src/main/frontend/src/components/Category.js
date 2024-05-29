import axios from 'axios';
import { AiOutlineDelete } from 'react-icons/ai';
import { useDispatch } from 'react-redux';
import { deleteCategory } from '../store/user';
import { useEffect, useState } from 'react';
import { CategoryModal } from './modals/CategoryModal';

export const CategoryV1 = ({ category }) => {
    const dispatch = useDispatch();

    const [showUpdateModal, setShowUpdateModal] = useState(false)

    const handleDeleteClick = async (e) => {
        e.stopPropagation();
        await axios
        .delete(`/category/${category.categoryId}`
        ).then((res)=>{
            dispatch(deleteCategory(category.categoryId));
           console.log(res.data) 
        }) 
    }

    const handleUpdateClick = async (e) => {
        setShowUpdateModal(true);
        
    }


    return (
        <div>
            <div id="category" onClick={handleUpdateClick} style={{ backgroundColor: category.color }}>
                <div>{category.name}</div>
                <div className="my-btn delete-btn" onClick={handleDeleteClick}>
                    <AiOutlineDelete />
                </div>
            </div>
            {showUpdateModal ? <CategoryModal type={'update'} category={category} setShowModal={setShowUpdateModal} /> : null}
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
            <div
                onClick={handleClick}
                style={{ backgroundColor: category.color }}
                className="category"
                id="category-v2"
            >
                <div>{category.name}</div>
            </div>

    );
};