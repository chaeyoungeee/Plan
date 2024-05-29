import axios from 'axios';
import { useEffect, useState } from 'react';
import { Form } from 'react-bootstrap';
import { useDispatch } from 'react-redux';
import { useNavigate } from 'react-router-dom';
import { FiSend } from 'react-icons/fi';
import { deletePlan, updatePlan } from '../../store/user';
import { AiOutlineDelete } from 'react-icons/ai';

export const UpdatePlanModal = ({ plan, setShowModal }) => {
    useEffect(()=>{
        if (plan) {
            setFormData({ title: plan.title, start: plan.start, end: plan.end });
        }
    }, [plan])

    const [formData, setFormData] = useState({ title: '', start: '', end: '' });

    const dispatch = useDispatch();

    const handleInputChange = (e) => {
        const { name, value } = e.target;
        setFormData({ ...formData, [name]: value });
    };

    const handleClick = (e) => {
        setShowModal(false);
    };

    const handleScreenClick = (e) => {
        e.stopPropagation();
    };

    const navigate = useNavigate();

    const handleSubmit = async (e) => {
        e.preventDefault();
        const data = {
            planId: plan.planId,
            title: formData.title,
            start: formData.start,
            end: formData.end,
        };

        await axios
            .put(`/plan/${plan.planId}`, data)
            .then((res) => {
                console.log(res.data);
                setShowModal(false);
                dispatch(updatePlan(data))
                navigate('/calendar');
            })
            .catch((e) => {
                console.log(e);
            });
    };

    const handleDeleteClick = async (e) => {
        e.stopPropagation();
        await axios.delete(`/plan/${plan.planId}`).then((res) => {
                dispatch(deletePlan(plan.planId))
                setShowModal(false);
    })
}

    if (!plan) {
        return null;
    }

    return (
        <div id="modal" onClick={handleClick}>
            <div onClick={handleScreenClick} className="add-screen" style={{ backgroundColor: plan.color }}>
                <div className='d-flex align-items-center justify-content-between'>
                    <div>{plan.categoryName}</div>
                    <div className="my-btn delete-btn" onClick={handleDeleteClick}>
                        <AiOutlineDelete size={16}/>
                    </div>
                </div>
                <Form className="d-flex align-items-center mt-2 mb-1" method="POST" id="plan" onSubmit={handleSubmit}>
                    <input
                        type="text"
                        value={formData.title}
                        onChange={handleInputChange}
                        name="title"
                        placeholder="할 일을 입력하세요."
                    ></input>
                    <input
                        required
                        type="text"
                        value={formData.start}
                        onChange={handleInputChange}
                        name="start"
                        placeholder="시작 날짜를 입력하세요."
                    ></input>
                    <input
                        required
                        type="text"
                        value={formData.end}
                        onChange={handleInputChange}
                        name="end"
                        placeholder="종료 날짜를 입력하세요."
                    ></input>
                    <button type="submit">
                        <FiSend />
                    </button>
                </Form>
            </div>
        </div>
    );
};
