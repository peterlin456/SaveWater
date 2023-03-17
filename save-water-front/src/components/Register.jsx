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
    password: '',
    phone:'',
    address:{
    },
    firstname:'',
    lastname:''

  });
  const [address,setAddress]=useState({
    street:'',
    city:'',
    state:'',
    country:'',
    zipcode:''
  });
  const {street,city,state,country,zipcode} = address;
  const {username,email,password,phone,firstname,lastname} = formValue;
  const handleAddress =(e)=>{
    setAddress({...address,[e.target.id]:e.target.value})
  }
  const handleInputChange = (event) => {
    setformValue({
      ...formValue,
      [event.target.id]: event.target.value,
    });

  }
  const handleSubmit=async(e)=>{
    formValue.address=address;
    e.preventDefault();
    try{
    await axios.post("http://localhost:8080/api/auth/signup",formValue)
    navigate('/')
    }catch(e){
      console.log(e)
    }
    console.log(formValue);
 
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
                          First Name
                        </Form.Label>
                        <Form.Control type="text" placeholder="Enter First Name"  id="firstname" value={firstname} onChange={(e)=>handleInputChange(e)}/>
                      </Form.Group>

                      <Form.Group className="mb-3">
                        <Form.Label className="text-center">
                          Last Name
                        </Form.Label>
                        <Form.Control type="text" placeholder="Enter Last Name"  id="lastname" value={lastname} onChange={(e)=>handleInputChange(e)}/>
                      </Form.Group>

                      <Form.Group className="mb-3">
                        <Form.Label className="text-center">
                          Username
                        </Form.Label>
                        <Form.Control type="text" placeholder="Enter Name"  id="username" value={username} onChange = {(e) => handleInputChange(e)}/>
                      </Form.Group>

                      <Form.Group className="mb-3">
                        <Form.Label className="text-center">
                          Phone Number
                        </Form.Label>
                        <Form.Control type="text" placeholder="Enter Phone Number"  id="phone" value={phone} onChange = {(e) => handleInputChange(e)}/>
                      </Form.Group>


                      <Form.Group className="mb-3">
                        <Form.Label className="text-center">
                          Street
                        </Form.Label>
                        <Form.Control type="text" placeholder="Enter street"  id="street" value={street} onChange = {(e) => handleAddress(e)}/>
                      </Form.Group>
                      <Form.Group className="mb-3">
                        <Form.Label className="text-center">
                          City
                        </Form.Label>
                        <Form.Control type="text" placeholder="Enter City"  id="city" value={city} onChange = {(e) => handleAddress(e)}/>
                      </Form.Group>
                      <Form.Group className="mb-3">
                        <Form.Label className="text-center">
                          State
                        </Form.Label>
                        <Form.Control type="text" placeholder="Enter State"  id="state" value={state} onChange = {(e) => handleAddress(e)}/>
                      </Form.Group>
                      <Form.Group className="mb-3">
                        <Form.Label className="text-center">
                          Country
                        </Form.Label>
                        <Form.Control type="text" placeholder="Enter Country"  id="country" value={country} onChange = {(e) => handleAddress(e)}/>
                      </Form.Group>

                      <Form.Group className="mb-3" >
                        <Form.Label className="text-center">
                          Zip Code
                        </Form.Label>
                        <Form.Control type="text" placeholder="Enter zipcode"  id="zipcode" value={zipcode} onChange = {(e) => handleAddress(e)}/>
                      </Form.Group>

                      <Form.Group className="mb-3" >
                        <Form.Label className="text-center">
                          Email
                        </Form.Label>
                        <Form.Control type="email" placeholder="Enter email"  id="email" value={email} onChange = {(e) => handleInputChange(e)}/>
                      </Form.Group>
                      <Form.Group
                        className="mb-3" >
                        <Form.Label>Password</Form.Label>
                        <Form.Control type="password" placeholder="Password"    id="password" value={password} onChange = {(e) => handleInputChange(e)}/>
                      </Form.Group>

                      <Form.Group
                        className="mb-3" >
                        <Form.Label>Confirm Password</Form.Label>
                        <Form.Control type="password" placeholder="Confirm Password" id="confirmPassword"/>
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