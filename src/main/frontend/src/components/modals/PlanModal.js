import { useSelector } from 'react-redux';
import { Rectangular } from '../Rectangular';
import { useState } from 'react';
import { SelectCategoryModal } from './SelectCategoryModal';

export const PlanModal = ({ isFriend, date, plans, setShowPlanModal }) => {
    const handleClick = (e) => {
        setShowPlanModal(false);
    };

    const handleScreenClick = (e) => {
        e.stopPropagation();
    };

    const [showModal, setShowModal] = useState(false);

    const handleAddBtnClick = (e) => {
        setShowModal(true);
    };

    return (
        <>
            <div id="modal" onClick={handleClick}>
                <div className="screen" onClick={handleScreenClick}>
                    <div className="date">{date}</div>
                    <div className="d-day">D + 6</div>
                    <div className="plans">
                        {plans != null
                            ? plans.map((plan) => <Rectangular key={plan.id} type={'plan'} data={plan} />)
                            : null}
                    </div>
                    {isFriend ?null:<div className="add" onClick={handleAddBtnClick}>
                        <Rectangular type={'add'}></Rectangular>
                    </div>}
                </div>
            </div>
            {showModal ? <SelectCategoryModal setShowPlanModal={setShowPlanModal} setShowCategoryModal={setShowModal} date={date} /> : null}
        </>
    );
};
