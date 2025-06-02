import { useState } from 'react'

import './App.css'
import { BrowserRouter, Route, Routes } from 'react-router-dom'
import LandingPage from './components/LandingPage'
import AboutPage from './components/AboutPage'
import NavBar from './components/NavBar'
import Footer from './components/Footer'
import "slick-carousel/slick/slick.css";
import "slick-carousel/slick/slick-theme.css";

function App() {
  const [count, setCount] = useState(0)

  return (
    <>
       <BrowserRouter>
       <NavBar />
            <Routes>
              <Route path='/' element={<LandingPage/>} />
              <Route path='/about' element={<AboutPage/>} />

            </Routes>
        <Footer />
       </BrowserRouter>
    </>
  )
}

export default App
