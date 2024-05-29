import axios from "axios";
import { useState } from "react";
import { Form, FormControl, FormGroup } from "react-bootstrap";
import { useDispatch, useSelector } from "react-redux";
import { redirect, useNavigate } from "react-router-dom";
import { FiSend } from 'react-icons/fi';
import { addPlan } from "../../store/user";

export const AddPlanModal = ({setShowPlanModal, setShowCategoryModal, setShowAddModal, date, category}) => {
   
    const [formData, setFormData] = useState({ title: '', start: date, end: date });
    const userId = useSelector(state => state.user.userId)

    const dispatch = useDispatch();

    const handleInputChange = (e) => {
        const { name, value } = e.target;
        setFormData({ ...formData, [name]: value });
    };

    const handleClick = (e) => {
        setShowAddModal(false);
    };

    const handleScreenClick = (e) => {
        e.stopPropagation();
    };


    const navigate = useNavigate();

    const handleSubmit = async (e) => {
        e.preventDefault();
        const data = {
            categoryId: category.categoryId,
            userId: userId,
            title: formData.title,
            start: formData.start,
            end: formData.end,
        };

        await axios
            .post('/plan', data)
            .then((res) => {
                console.log(res.data)
                dispatch(addPlan(res.data))
                setShowAddModal(false)
                setShowCategoryModal(false)
                setShowPlanModal(false)
                navigate('/calendar');
            })
            .catch((e) => {
                console.log(e);
            });
    };

    
    return (
        <div id="modal" onClick={handleClick}>
            <div onClick={handleScreenClick} className="add-screen" style={{ backgroundColor: category.color }}>
                <div>{category.name}</div>
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

}