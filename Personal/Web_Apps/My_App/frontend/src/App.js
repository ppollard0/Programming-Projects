// Basic React app setup with routing
import './css/index.css';
import React from 'react';
import { BrowserRouter as Router, Route, Routes, Navigate } from 'react-router-dom';

// Importing the page components
import Home from './pages/Home';
import Lost from './pages/Lost';


export default function App () {
    
    return (
        <Router>
            <Routes>
                <Route path="/" element={<Home />} />
                <Route path="/404" element={<Lost />} />
                <Route path="*" element={<Navigate to="/404" replace />} />
            </Routes>
        </Router>
    )
}