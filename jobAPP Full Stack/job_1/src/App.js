import { Search } from '@mui/icons-material';
import './App.css';
import { BrowserRouter, Routes, Route } from "react-router-dom";
import Create from './components/Create';
import Navbar from './components/Navbar';
import Edit from './components/Edit';
import ViewAllJobs from './components/ViewAllJobs';

function App() {
  return (
  <>
   <Navbar/>
   <BrowserRouter>
   <Routes>
   <Route path='/' element={<ViewAllJobs/>}/>
   <Route path="/create" element={<Create />} />
   <Route path="/edit" element={<Edit />} />
   </Routes>
   </BrowserRouter> 
  </>
  );
}

export default App;