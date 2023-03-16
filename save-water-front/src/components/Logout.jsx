import React from 'react'
import { Col, Button, Row, Container, Card, Form,Alert } from "react-bootstrap";
import { useSignOut } from 'react-auth-kit'
import { useNavigate } from 'react-router-dom';
export default function Logout() {
  const signOut =useSignOut('');
  const navigate = useNavigate('');
  const logout =()=>{
    signOut();
    navigate('/login');
  }
  return (
    <Container>
    <Button onClick={logout}>Log out</Button>
    </Container>
  )
}
