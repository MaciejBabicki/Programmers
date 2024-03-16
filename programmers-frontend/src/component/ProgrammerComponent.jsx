import React, { useState } from 'react'
import { createProgrammer } from '../service/ProgrammerService';
import { useNavigate } from 'react-router-dom';

const ProgrammerComponent = () => {
    const [firstName, setFirstName] = useState('')
    const [lastName, setLastName] = useState('')

    const navigator = useNavigate();

    function handleFirstName(e){
        setFirstName(e.target.value);
    }

    function  saveProgrammer(e) {
        e.preventDefault();
        const programmer = {firstName, lastName}
        console.log(programmer)

        createProgrammer(programmer).then((response) => {
            console.log(response.data);
            navigator('/programmer')
        })
    }

      return (
    <div className='container'>
        <div className='row'>
            <div className='card col-md-6 offset-md-3 offset-md-3'>
                <h2 className='text-center'>Add Programmer</h2>
                <div className='card-body'>
                    <form>
                        <div className='form-group mb-5'>
                            <label className='form-label'>First Name: </label>
                            <input
                                type='text'
                                placeholder = 'Enter Employee FIrst Name'
                                name='firstName'
                                value={firstName}
                                className='form-control'
                                onChange={handleFirstName}
                                >
                            </input>
                        </div>
                        <button className='btn btn-success' onClick={saveProgrammer}>Submit</button>
                    </form>

                </div>

            </div>

        </div>
        
    </div>
  )
}

export default ProgrammerComponent