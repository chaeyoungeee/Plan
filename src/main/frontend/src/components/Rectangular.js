import axios from 'axios';
import { useEffect, useState } from 'react';
import { MdCheckBox } from 'react-icons/md';
import { MdCheckBoxOutlineBlank } from 'react-icons/md';
import { useDispatch, useSelector } from 'react-redux';
import { toggleStatus } from '../store/user';
import { UpdatePlanModal } from './modals/UpdatePlanModal';

export const Rectangular = ({ isFriend, type, data }) => {
    const dispatch = useDispatch();
    const plans = useSelector((state) => state.user.plans);
    const [color, setColor] = useState('#eaedf3');
    const [status, setStatus] = useState('')

    const [showUpdateModal, setShowUpdateModal] = useState(false);

    const handleUpdateClick = () => {
        setShowUpdateModal(true)
    }

    const handleCheckClick = async (e) => {
        e.stopPropagation();
        await axios.put(`/plan/status/${data.planId}`).then((res) => {
            dispatch(toggleStatus(data.planId));
            if (status === 'INCOMPLETE') setStatus('COMPLETED')
            else setStatus('INCOMPLETE');
        })
    };

    useEffect(() => {
        if (data) {
                    console.log(1);
                    console.log(isFriend);
            setStatus(data.status);
            setColor(data.color);
        }
    }, []);


    if ((!data || !plans.length) && type == 'plan') {
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
                    {type === 'plan' ? <div className="category">{data.categoryName}</div> : null}
                    {type === 'plan' ? <div className="title">{data.title}</div> : 
                    (
                        isFriend === true ?
                        '+ 함께 할 일을 추가하세요.':                        
                        '+ 할 일을 추가하세요.'
                    )}
                </div>
                <div onClick={handleCheckClick}>
                    {type === 'plan' && !isFriend ? (
                        status === 'INCOMPLETE' ? (
                            <MdCheckBoxOutlineBlank size={15} />
                        ) : (
                            <MdCheckBox size={15} />
                        )
                    ) : null}
                </div>
            </div>
            { showUpdateModal && !isFriend ?            <UpdatePlanModal setShowModal={setShowUpdateModal} plan={data} /> : null}
        </div>
    );
};
