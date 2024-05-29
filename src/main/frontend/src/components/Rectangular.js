import axios from 'axios';
import { useEffect, useState } from 'react';
import { MdCheckBox } from 'react-icons/md';
import { MdCheckBoxOutlineBlank } from 'react-icons/md';
import { useDispatch, useSelector } from 'react-redux';
import { toggleStatus } from '../store/user';
import { UpdatePlanModal } from './modals/UpdatePlanModal';

export const Rectangular = ({ type, data }) => {
    const dispatch = useDispatch();
    const plans = useSelector((state) => state.user.plans);
    const [color, setColor] = useState('#eaedf3');

    let plan = null;

    const [showUpdateModal, setShowUpdateModal] = useState(false);

    const handleUpdateClick = () => {
        setShowUpdateModal(true)
    }

    const handleCheckClick = async (e) => {
        e.stopPropagation();
        await axios.put(`/plan/status/${data.planId}`).then((res) => {
            dispatch(toggleStatus(data.planId));
        })
    };

    useEffect(() => {
        if (data) {
            setColor(data.color);
        }
    }, []);



    if ((!data || !plans.length) && type == 'plan') {

            return null;
    }

   if (type == 'plan') {
        plan = plans.find((plan) => plan.planId === data.planId);
   }

   if (type =='plan' && plan == null) {
        return null;
   }


    return (
        <div>
            <div
                id="rectangular"
                onClick={handleUpdateClick}
                className="d-flex flex-row justify-content-between align-items-center"
                style={{ backgroundColor: color }}
            >
                <div>
                    {type === 'plan' ? <div className="category">{plan.categoryName}</div> : null}
                    {type === 'plan' ? <div className="title">{plan.title}</div> : '+ 할 일을 추가하세요'}
                </div>
                <div onClick={handleCheckClick}>
                    {type === 'plan' ? (
                        plan.status === 'INCOMPLETE' ? (
                            <MdCheckBoxOutlineBlank size={15} />
                        ) : (
                            <MdCheckBox size={15} />
                        )
                    ) : null}
                </div>
            </div>
            { showUpdateModal ?            <UpdatePlanModal setShowModal={setShowUpdateModal} plan={plan} /> : null}
        </div>
    );
};
