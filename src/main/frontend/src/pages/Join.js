import axios from 'axios';
import { useState } from 'react';
import { FormGroup } from 'react-bootstrap';
import Button from 'react-bootstrap/Button';
import Col from 'react-bootstrap/Col';
import Form from 'react-bootstrap/Form';
import InputGroup from 'react-bootstrap/InputGroup';
import Row from 'react-bootstrap/Row';
import { useNavigate } from 'react-router-dom';

export const Join = () => {
    const navigate = useNavigate();

    const [formData, setFormData] = useState({ username: '', nickname: '', password: '' });


    const handleInputChange = (e) => {
        const { name, value } = e.target;
        setFormData({ ...formData, [name]: value });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        await axios
            .post('/signup', {
                username: formData.username,
                nickname: formData.nickname,
                password: formData.password
            }).then(()=>{
                navigate('/mypage')
            }).catch((e)=>{
            })
    };

    return (
        <Form id="join" onSubmit={handleSubmit}>
            <Row className="mb-3">
                <FormGroup className="mb-3" as={Col} md="4">
                    {/* <Form.Label>Username</Form.Label> */}
                    <Form.Control
                        required
                        type="text"
                        value={formData.username}
                        onChange={handleInputChange}
                        name="username"
                        placeholder="Username"
                    />
                </FormGroup>
                {/* 
                <FormGroup as={Col} md="4">
                    <Form.Label>Nickname</Form.Label>
                    <Form.Control required type="text" placeholder="Nickname" />
                </FormGroup> */}
                <FormGroup className="mb-3" as={Col} md="4">
                    {/* <Form.Label>Password</Form.Label> */}
                    <Form.Control
                        required
                        type="text"
                        value={formData.nickname}
                        onChange={handleInputChange}
                        name="nickname"
                        placeholder="Nickname"
                    />
                </FormGroup>

                <FormGroup as={Col} md="4">
                    {/* <Form.Label>Password</Form.Label> */}
                    <Form.Control
                        required
                        name="password"
                        value={formData.password}
                        onChange={handleInputChange}
                        type="password"
                        placeholder="Password"
                    />
                </FormGroup>
            </Row>
            <Button type="submit" className="btn-danger">
                Signup
            </Button>
        </Form>
    );
};
