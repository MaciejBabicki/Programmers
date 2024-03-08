
import './App.css'
import FooterComponent from './components/FooterComponent'
import HeaderComponent from './components/HeaderComponent'
import ListTrainerComponent from './components/ListTrainerComponent'
import {BrowserRouter, Routes, Route} from 'react-router-dom'
import TrainerComponent from './components/TrainerComponent'

function App() {


  return (
    <>
      <BrowserRouter>
        <HeaderComponent/>
        <Routes>
          {/* //http://localhost:3000 */}
          <Route path ="/" element = { <ListTrainerComponent/> }></Route>
          {/* //http://localhost:3000/trainers */}
          <Route path ="/trainers" element = { <ListTrainerComponent/> }></Route>
           {/* //http://localhost:3000/add-trainer */}
           <Route path ="/add-trainer" element = { <TrainerComponent/> }></Route>

           {/* //http://localhost:3000/edit-trainer/{id} */}
           <Route path='/edit-trainer/:id' element = {<TrainerComponent />}></Route>

        </Routes>
        
        <FooterComponent/>
      </BrowserRouter>
    </>
  )
}

export default App
