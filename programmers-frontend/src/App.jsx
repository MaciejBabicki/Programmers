import { useState } from 'react'
import './App.css'
import ListProgrammerComponent from './component/ListProgrammerComponent'
import HeaderComponent from './component/HeaderComponent'
import FooterComponent from './component/FooterComponent'
import { BrowserRouter,Routes, Route} from 'react-router-dom'
import ProgrammerComponent from './component/ProgrammerComponent'

function App() {
  const [count, setCount] = useState(0)

  return (
    <> 
    <BrowserRouter>
    <HeaderComponent/>
    <Routes>
      {/* //http://localhost:3000 */}
      <Route path='/' element = {<ListProgrammerComponent/>}></Route>
      {/* //http://localhost:3000/programmers */}
      <Route path='/programmer' element = {<ListProgrammerComponent/>}> </Route>
      {/* //http://localhost:3000/add-programmer */}
      <Route path='add-programmer' element = {<ProgrammerComponent/>}></Route>
    </Routes>
     <FooterComponent/>
     </BrowserRouter>
    </>
  )
}

export default App
