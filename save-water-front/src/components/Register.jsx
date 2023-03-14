import React from 'react';
import { Col, Button, Row, Container, Card, Form } from "react-bootstrap";
import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
export default function Regsiter() {
  // const[name,setName]= useState('');
  // const[username,setUsername]= useState('');
  // const [email, setEmail] = useState('');
  // const [password,setPassword] = useState('');
  // const [confirmPassword,setConfirmPassword] = useState('');
  const navigate = useNavigate('');
  const [formValue, setformValue] =useState({

    username:'',
    email: '',
    password: ''

  });
  const {username,email,password} = formValue;
  const handleInputChange = (event) => {
    setformValue({
      ...formValue,
      [event.target.id]: event.target.value
    });
  }
  const handleSubmit=async(e)=>{
    e.preventDefault();
    try{
    await axios.post("http://localhost:8080/api/v1/user/save",formValue)
    navigate('/')
    }catch(e){
      console.log(e)
    }
 
};
  return (
    <div>
      <Container>
        <Row className="vh-100 d-flex justify-content-center align-items-center">
          <Col md={8} lg={6} xs={12}>
          <div className="border border-2 border-primary"></div>
            <Card className="shadow px-4">
              <Card.Body>
                <div className="mb-3 mt-md-4">
                  <h2 className="fw-bold mb-2 text-center text-uppercase ">Logo</h2>
                  <div className="mb-3">
                    <Form>
                      <Form.Group className="mb-3">
                        <Form.Label className="text-center">
                          Full Name
                        </Form.Label>
                        <Form.Control type="text" placeholder="Enter Name"  id="name"/>
                      </Form.Group>
                      <Form.Group className="mb-3">
                        <Form.Label className="text-center">
                          Username
                        </Form.Label>
                        <Form.Control type="text" placeholder="Enter Name"  id="username" value={username} onChange = {(e) => handleInputChange(e)}/>
                      </Form.Group>

                      <Form.Group className="mb-3" >
                        <Form.Label className="text-center">
                          Email address
                        </Form.Label>
                        <Form.Control type="email" placeholder="Enter email"  id="email" value={email} onChange = {(e) => handleInputChange(e)}/>
                      </Form.Group>

                      <Form.Group
                        className="mb-3"
                      
                      >
                        <Form.Label>Password</Form.Label>
                        <Form.Control type="password" placeholder="Password"    id="password" value={password} onChange = {(e) => handleInputChange(e)}/>
                      </Form.Group>
                      <Form.Group
                        className="mb-3"
                        
                      >
                        <Form.Label>Confirm Password</Form.Label>
                        <Form.Control type="password" placeholder="Password" id="confirmPassword"/>
                      </Form.Group>
                      <Form.Group
                        className="mb-3"
                        controlId="formBasicCheckbox"
                      >
                      </Form.Group>
                      <div className="d-grid">
                        <Button variant="primary" type="submit" onClick={(handleSubmit)}>
                          Create Account
                        </Button>
                      </div>
                    </Form>
                    <div className="mt-3">
                      <p className="mb-0  text-center">
                      Already have an account??{" "}
                        <a href="/login" className="text-primary fw-bold">
                          Sign In
                        </a>
                      </p>
                    </div>
                  </div>
                </div>
              </Card.Body>
            </Card>
          </Col>
        </Row>
      </Container>
    </div>
  )
}