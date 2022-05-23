import React, { Component } from 'react';
import { Route, Routes } from 'react-router-dom';

import Home from './Home';
import ClientList from './client/ClientList';
import ClientEdit from "./client/ClientEdit";
import AppNavBar from './AppNavBar';

import './App.css';
import Students from './student/Sudent';

class App extends Component {
  render() {
    return (
      <div className="App">
        <AppNavBar />
        <Routes>
          <Route path="/" element={<Home />} />
          
          <Route path="clients" element={<ClientList />} />
          <Route path="clients/:id" element={<ClientEdit />} />

          <Route path="students" element={<Students />} />
        </Routes>
      </div>
    );
  }
}

export default App;