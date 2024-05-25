import { useSelector } from 'react-redux';
import { Rectangular } from './Rectangular';

export const AddPlanModal = ({ date, plans, setShowModal }) => {
    const handleClick = (e) => {
        setShowModal(false);
    };

    const handleScreenClick = (e) => {
        e.stopPropagation();
    };

    return (
        <div id="modal" onClick={handleClick}>
            <div className="screen" onClick={handleScreenClick}>
                <div className="date">{date}</div>
                <div className="d-day">D + 6</div>
                <div className="plans">
                    {plans != null
                        ? plans.map((plan) => <Rectangular key={plan.id} type={'plan'} data={plan} />)
                        : null}
                </div>
                <div className="add">
                    <Rectangular type={'add'}></Rectangular>
                </div>
            </div>
        </div>
    );
};
