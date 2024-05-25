import axios from 'axios';
import { useState } from 'react';
import { FormGroup } from 'react-bootstrap';
import Button from 'react-bootstrap/Button';
import Col from 'react-bootstrap/Col';
import Form from 'react-bootstrap/Form';
import InputGroup from 'react-bootstrap/InputGroup';
import Row from 'react-bootstrap/Row';
import { useDispatch } from 'react-redux';
import { useNavigate } from 'react-router-dom';
import { initUser } from '../store/user';

export const Login = () => {
    const navigate = useNavigate();
    const dispatch = useDispatch();

    const [formData, setFormData] = useState({ username: '', password: '' });

    const handleInputChange = (e) => {
        const { name, value } = e.target;
        setFormData({ ...formData, [name]: value });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        await axios
            .post('/login', {
                username: formData.username,
                password: formData.password
            }).then((res)=>{
                console.log(formData)
                alert(res.data.nickname + '님 환영합니다.');
                dispatch(initUser(res.data))
                navigate('/')
            }).catch((e)=>{
                console.log(e)
            })
    };

    return (
        <Form method="POST" id="login" onSubmit={handleSubmit}>
            <Row className="mb-3">
                <FormGroup className="mb-3" as={Col} md="6">
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
                <FormGroup as={Col} md="6">
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
            <Button type="submit" className='mb-3'>
                Login
            </Button>
            <Button type="button" className="btn-danger"
            onClick={() => {
                navigate('/signup')
            }}>
                Signup
            </Button>
        </Form>
    );
};
