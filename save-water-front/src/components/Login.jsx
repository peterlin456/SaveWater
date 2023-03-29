import { Col, Button, Row, Container, Card, Form,Alert } from "react-bootstrap";
import {useState} from 'react';
import axios from 'axios';
import { useSignIn } from "react-auth-kit";
import { useNavigate } from "react-router-dom";

export default function Login(props) {
    const [state, setState] = useState({
      username: "",
      password: ""
    });
    const navigate = useNavigate('');
    const signIn = useSignIn();
    const [error,setError] = useState('');
    const [fail,setFail] =useState('');
    const handleChange = (e) => {
      const value = e.target.value;
      setState({
        ...state,
        [e.target.name]: value
      });
    };
    const checkSignin= () =>{
      props.setIsLoggedIn(true);
    }
    const handleSubmit = async(e) => {
      e.preventDefault();
      if (state.username === "" || state.password === "") {
        setError("Please Enter Email and Password");
        return;
      }
      const userData = {
        username: state.username,
        password: state.password
      };
      await axios.post("http://localhost:8080/api/auth/signin", userData).then((response) => {
        signIn({
          token:response.data.token,
          expiresIn:3600,
          tokenType:'Bearer',
          authState:{username:userData.username}
        });
      
        if(response.data.status === false){
          setFail("Email or Password is not Correct");
        }
        if(response.data.accessToken){
          localStorage.setItem("user",JSON.stringify(response.data));
        }
        checkSignin();
        navigate('/userdash');
        return response.data;
      });
    };
   
  return (
    <div>
      <Container>
        <Row className="vh-100 d-flex justify-content-center align-items-center">
          <Col md={8} lg={6} xs={12}>
            <div className="border border-3 border-primary"></div>
            <Card className="shadow">
              <Card.Body>
                <div className="mb-3 mt-md-4">
                  <h2 className="fw-bold mb-2 text-uppercase ">Brand</h2>
                  <p className=" mb-5">Please enter your login and password!</p>
                  <div className="mb-3">
                    <Form onSubmit={handleSubmit}>
                      <Form.Group className="mb-3" controlId="formBasicEmail">
                        <Form.Label className="text-center">
                          Username
                        </Form.Label>
                        <Form.Control type="text" placeholder="Enter username" name="username"  value={state.username} onChange={handleChange}/>
                      </Form.Group>

                      <Form.Group
                        className="mb-3"
                        controlId="formBasicPassword"
                      >
                        <Form.Label>Password</Form.Label>
                        <Form.Control type="password" placeholder="Password" name="password" value={state.password} onChange={handleChange}/>
                      </Form.Group>
                      <Form.Group
                        className="mb-3"
                        controlId="formBasicCheckbox"
                      >
                        <p className="small">
                          <a className="text-primary" href="/forgot">
                            Forgot password?
                          </a>
                        </p>
                      </Form.Group>
                      {error  &&(
                          <Alert
                          severity="error" onClick={() => setError(null)}
                          >
                            {error}
                          </Alert>
                        )}
                        {fail  &&(
                          <Alert
                          severity="error" onClick={() => setFail(null)}
                          >
                            {fail}
                          </Alert>
                        )}
                      <div className="d-grid">
                        <Button variant="primary" type="submit">
                          Login
                        </Button>
                        
                      </div>
                    </Form>
                    <div className="mt-3">
                      <p className="mb-0  text-center">
                        Don't have an account?{" "}
                        <a href="/register" className="text-primary fw-bold">
                          Sign Up
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
  );
}
