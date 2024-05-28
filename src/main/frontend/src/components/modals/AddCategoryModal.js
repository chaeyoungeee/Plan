import axios from 'axios';
import { useState } from 'react';
import { Form, FormControl, FormGroup } from 'react-bootstrap';
import { useDispatch, useSelector } from 'react-redux';
import { redirect, useNavigate } from 'react-router-dom';
import { FiSend } from 'react-icons/fi';
import { addCategory, addPlan } from '../../store/user';

export const AddCategoryModal = ({ setShowAddModal }) => {
    const [formData, setFormData] = useState({ name: '', color: '#fef2d8' });
    const userId = useSelector((state) => state.user.userId);

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
            userId: userId,
            name: formData.name,
            color: formData.color
        };

        await axios
            .post('/category', data)
            .then((res) => {

               dispatch(addCategory(res.data));
            })
            .catch((e) => {
                console.log(e);
            });
    };

    return (
        <div id="modal" className='category-modal' onClick={handleClick}>
            <div
                onClick={handleScreenClick}
                className="add-screen category-screen"
                style={{ backgroundColor: '#eaedf3' }}
            >
                <div>카테고리</div>
                <Form
                    className="d-flex justify-content-between mt-2 mb-1"
                    method="POST"
                    id="category"
                    onSubmit={handleSubmit}
                >
                    <div className="d-flex align-item-center">
                        <input
                            id="color"
                            required
                            type="color"
                            value={formData.color}
                            onChange={handleInputChange}
                            name="color"
                        ></input>
                        <input
                            type="text"
                            value={formData.name}
                            onChange={handleInputChange}
                            name="name"
                            placeholder="카테고리명을 입력하세요."
                        ></input>
                    </div>
                    <button type="submit">
                        <FiSend />
                    </button>
                </Form>
            </div>
        </div>
    );
};
