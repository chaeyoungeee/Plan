import { useEffect, useState } from 'react';
import { CategoryV2 } from '../Category';
import { Category } from '../Category';
import { useSelector } from 'react-redux';
import { Row, Stack } from 'react-bootstrap';
import { AddPlanModal } from './AddPlanModal';

export const SelectCategoryModal = ({ setShowPlanModal, setShowCategoryModal, date }) => {
    const [category, setCategory] = useState(null)
    const [showModal, setShowModal] = useState(false)
    const handleClick = (e) => {
        setShowCategoryModal(false);
    };

    const handleScreenClick = (e) => {
        e.stopPropagation();
    };

    const categories = useSelector((state) => state.user.categories);

    useEffect(() => {
        console.log(categories);
    });

    return (
        <>
            <div id="modal" onClick={handleClick}>
                <div className="screen category-screen" onClick={handleScreenClick}>
                    <div className="title">카테고리 선택</div>
                    <div className="categories">
                        {categories.map((category) => (
                            <CategoryV2 key={category.id} category={category} setCategory={setCategory} setShowModal={setShowModal} />
                        ))}
                    </div>
                </div>
            </div>
            { showModal ? <AddPlanModal setShowPlanModal={setShowPlanModal} setShowCategoryModal={setShowCategoryModal} setShowAddModal={setShowModal} date={date} category={category} /> : null}
        </>
    );
};
