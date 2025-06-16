import { useState } from 'react'

import './App.css'
import { BrowserRouter, Route, Routes } from 'react-router-dom'
import LandingPage from './components/LandingPage'
import AboutPage from './components/AboutPage'
import NavBar from './components/NavBar'
import Footer from './components/Footer'
import "slick-carousel/slick/slick.css";
import "slick-carousel/slick/slick-theme.css";
import RegisterPage from './components/RegisterPage'
import LoginPage from './components/LoginPage'
import {Toaster} from 'react-hot-toast'
import DashboardLayout from './components/DashBoard/DashboardLayout'
function App() {
  const [count, setCount] = useState(0)

  return (
    <>
       <BrowserRouter>
       <NavBar />
       <Toaster/>
            <Routes>
              <Route path='/' element={<LandingPage/>} />
              <Route path='/about' element={<AboutPage/>} />
              <Route path='/register' element={<RegisterPage/>} />
              <Route path='/login' element={<LoginPage/>} />
               <Route path='/dashboard' element={<DashboardLayout/>} />
            </Routes>
        <Footer />
       </BrowserRouter>
    </>
  )
}

export default App
