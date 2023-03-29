import React from 'react'
import { Col, Button, Row, Container, Card, Form,Alert } from "react-bootstrap";
export default function Forgot() {
    const handleSubmit = e =>{
        e.preventDefault();
    }
    const handleChange = e =>{
        e.preventDefault();
    }
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
                      <p className=" mb-5">Please enter your Email!</p>
                      <div className="mb-3">
                        <Form onSubmit={handleSubmit}>
                          <Form.Group className="mb-3" controlId="formBasicEmail">
                            <Form.Label className="text-center">
                              Email
                            </Form.Label>
                            <Form.Control type="email" placeholder="Enter Email" name="email"  onChange={handleChange}/>
                          </Form.Group>
                         
                        </Form>
                        <Button>Sent Email Verification</Button>
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
    
