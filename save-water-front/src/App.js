
import './App.css';
import {BrowserRouter as Router,Routes,Route} from 'react-router-dom'
import Navb from './components/Navb';
import Home from './components/Home';
import Register from './components/Register';
import Login from './components/Login';
import About from './components/About';
import Contact from './components/Contact';
import Protection from './components/Protection';
import Logout from './components/Logout';
import styled from 'styled-components';
import { RequireAuth } from 'react-auth-kit';
import { useState } from 'react';
import UserDash from './components/UserDash';

const AppContainer = styled.div`
width:100%;
height:100%
`;
function App() {
  const [isLoggedIn, setIsLoggedIn] = useState(false);
  return (
    <AppContainer> 
       <Navb isLoggedIn={isLoggedIn}/>
      <Routes>
        <Route path='/userdash' element={<RequireAuth loginPath='/login'>
          <UserDash/>
        </RequireAuth>}/>
        <Route path='/' element={<Home/>}/>
        <Route path='/register' element={<Register/>}/>
        <Route path='/login' element={<Login isLoggedIn={isLoggedIn} setIsLoggedIn={setIsLoggedIn}/>}/>
        <Route path='/logout' element={<Logout/>}/>
        {/* <Route path="*" element={<Protection/>} /> */}
        <Route path='/about' element={<About/>}/>
        <Route path='/contact' element={<Contact/>}/>
        </Routes>
      </AppContainer>
  
  );
}

export default App;
