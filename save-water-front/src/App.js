
import './App.css';
import {BrowserRouter as Router,Routes,Route} from 'react-router-dom'
import Navb from './components/Navb';
import Home from './components/Home';
import Register from './components/Register';
import Login from './components/Login';
import About from './components/About';
import Contact from './components/Contact';
function App() {
  return (
    <Router>
      <Navb/>
      <Routes> 
        <Route path='/' element={<Home/>}/>
        <Route path='/register' element={<Register/>}/>
        <Route path='/login' element={<Login/>}/>
        <Route path='/about' element={<About/>}/>
        <Route path='/contact' element={<Contact/>}/>
      </Routes>

    </Router>
  );
}

export default App;
