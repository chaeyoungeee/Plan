import axios from 'axios';
import { useEffect, useState } from 'react';
import { MdCheckBox } from 'react-icons/md';
import { MdCheckBoxOutlineBlank } from 'react-icons/md';
import { useDispatch, useSelector } from 'react-redux';
import { toggleStatus } from '../store/user';

export const Rectangular = ({ type, data }) => {
    const dispatch = useDispatch();
    const plans = useSelector((state) => state.user.plans);
    const [color, setColor] = useState('#eaedf3');

    const handleCheckClick = async (e) => {
        e.stopPropagation();
        await axios.put(`/plan/${data.planId}`).then((res) => {
            dispatch(toggleStatus(data.planId));
        })
    };

    useEffect(() => {
        if (data && plans.length > 0) {
            const plan = plans.find((plan) => plan.planId === data.planId);
            if (plan) {
                setColor(plan.color);
            }
        }
    }, [data, plans]);

    if (!data || !plans.length) {
        return null;
    }

    const plan = plans.find((plan) => plan.planId === data.planId);

    return (
        <div
            id="rectangular"
            className="d-flex flex-row justify-content-between align-items-center"
            style={{ backgroundColor: color }}
        >
            <div>
                {type === 'plan' ? <div className="category">{plan.categoryName}</div> : null}
                {type === 'plan' ? <div className="title">{plan.title}</div> : '+ 할 일을 추가하세요'}
            </div>
            <div onClick={handleCheckClick}>
                {type === 'plan' ? (
                    plan.status === 'COMPLETED' ? (
                        <MdCheckBoxOutlineBlank size={15} />
                    ) : (
                        <MdCheckBox size={15} />
                    )
                ) : null}
            </div>
        </div>
    );
};
