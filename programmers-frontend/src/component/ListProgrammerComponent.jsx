import React, {useEffect, useState} from 'react'
import { listProgrammers } from '../service/ProgrammerService'
import { useNavigate } from 'react-router-dom'

const ListProgrammerComponent = () => {
   
   const [programmers, setProgrammers] = useState([])
   const navigator = useNavigate();

   useEffect(() => {
    listProgrammers().then((response) => {
        setProgrammers(response.data);
    }).catch(error => {
        console.error(error);
    })
    }, []) 

    function addNewProgrammer(){
        navigator('/add-programmer')
    }

  return (
    <div className='container'>
        <h2 className='text-center'>List of Programmers</h2>
        <button className='btn btn-primary mb-5'onClick={addNewProgrammer}>Add Programmer</button>
        <table className='table table stripped table-bordered'>
            <thead>
                <tr>
                    <th>Id</th>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>repoName (data from Github)</th>
                    
                </tr>
            </thead>
            <tbody>
                {
                    programmers.map(programmer =>
                        <tr key = {programmer.id}>
                            <td>{programmer.id}</td>
                            <td>{programmer.firstName}</td>
                            <td>{programmer.lastName}</td>
                            <td>{programmer.repoName}</td>
                        </tr> )
                }
            </tbody>
        </table>
    </div>
  )
}

export default ListProgrammerComponent