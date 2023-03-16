import React from 'react'
import {Button} from "react-bootstrap";
import Container from 'react-bootstrap/Container';
import Nav from 'react-bootstrap/Nav';
import Navbar from 'react-bootstrap/Navbar';
import Logout from './Logout';
// import NavDropdown from 'react-bootstrap/NavDropdown';
export default function Navb() {
  return (
    <Navbar collapseOnSelect expand="lg" bg="dark" variant="dark">
    <Container>

      <Navbar.Brand href="/"> <img
              alt=""
              src="https://lirp.cdn-website.com/3647da67/dms3rep/multi/opt/Bluechips_Goal_No14icon-1920w.png"
              width="30"
              height="30"
              className="d-inline-block align-top"
            />{' '}Life Below Water</Navbar.Brand>
      <Navbar.Toggle aria-controls="responsive-navbar-nav" />
      <Navbar.Collapse id="responsive-navbar-nav">
        <Nav className="me-auto">
          <Nav.Link href="/about">About Us</Nav.Link>
          <Nav.Link href="/contact">Contact Us</Nav.Link>
          {/* <NavDropdown title="Dropdown" id="collasible-nav-dropdown">
            <NavDropdown.Item href="#action/3.1">Action</NavDropdown.Item>
            <NavDropdown.Item href="#action/3.2">
              Another action
            </NavDropdown.Item>
            <NavDropdown.Item href="#action/3.3">Something</NavDropdown.Item>
            <NavDropdown.Divider />
            <NavDropdown.Item href="#action/3.4">
              Separated link
            </NavDropdown.Item>
          </NavDropdown> */}
        </Nav>
        <Nav>
          
          <Button variant="dark" href='/register'>Sign Up</Button>
          <Button variant="dark" href='/login'>Login</Button>
          
          

        </Nav>
      </Navbar.Collapse>
    </Container>
  </Navbar>
  )
}
