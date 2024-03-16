import React, { useEffect, useState } from 'react'
import { createTrainer, getTrainer, updateTrainer } from '../service/TrainerService'
import { useNavigate, useParams } from 'react-router-dom'

const TrainerComponent = () => {

    const [firstName, setFirstName] = useState('')
    const [lastName, setLastName] = useState('')
    const [specialization, setSpecialization] = useState('')
    const [repository, setRepository] = useState([]);

    const{id} = useParams();

    const [errors, setErrors] = useState({
        firstName: '',
        lastName: '',
        specialization: '',
        repository: '',
    })

    const navigator = useNavigate();

    useEffect(() => {
        if(id){
            getTrainer(id).then((response) => {
                setFirstName(response.data.firstName);
                setLastName(response.data.lastName);
                setSpecialization(response.data.specialization);
                setRepository(response.data.repository);
            }).catch(error => {
                console.error(error);
            })
        }
    }, [id])

    function handleFirstName(e){
        setFirstName(e.target.value);
    }
    function handleLastName(e){
        setLastName(e.target.value);
    }
    function handleSpecialization(e){
        setSpecialization(e.target.value);
    }
    function handleRepository(e){
        setRepository(e.target.value);
    }
    function saveOrUpdateTrainer(e){
        e.preventDefault();

        if(validationForm()){

            const trainer = {firstName, lastName, specialization}
        console.log(trainer)

            if(id){
                updateTrainer(id, trainer).then((response) => {
                    console.log(response.data);
                    navigator('trainers');
                }).catch(error => {
                    console.error(error);
                })
            }else{
                createTrainer(trainer).then((response) => {
                    console.log(response.data);
                    navigator('/trainers')
                }).catch(error => {
                    console.error(error);
                })
            }
        }
    }
    function validationForm(){
        let valid = true;

        const errorsCopy = {... errors}

        if(firstName.trim()){
            errorsCopy.firstName = '';
        }else{
            errorsCopy.firstName = 'First name is required';
            valid = false;
        }

        if(lastName.trim()){
            errorsCopy.lastName = '';
        }else{
            errorsCopy.lastName = 'Last name is required';
            valid = false;
        }

        if(specialization.trim()){
            errorsCopy.specialization = '';
        }else{
            errorsCopy.specialization = 'Specialization is required';
            valid=false;
        }
        if(specialization.trim()){
            errorsCopy.repository = '';
        }else{
            errorsCopy.repository = 'Repository is required';
            valid=false;
        }
        setErrors(errorsCopy);
        
        return valid;   
    }
        
    function pageTitle(){
        if(id){
            return <h2 className='text-center'>Update Trainer</h2>
        }else{
            return <h2 className='text-center'>Add Trainer</h2>
        }


    }


  return (
    <div className='container'>
        <div className='row'>
            <div className='card col-md-6 offset-md-3 offset-md-3'>
                {
                    pageTitle()
                }
                <div className='card-body'>
                    <form>
                        <div className='form-group mb-2'>
                            <label className='form-label'>First Name:</label>
                            <input
                                type = 'test'
                                placeholder='Enter Trainer First Name'
                                name = 'firstName'
                                value={firstName}
                                className={`form-control ${errors.firstName ? 'is-invalid': ''}`}
                                onChange={handleFirstName}
                            >
                            </input>
                            {errors.firstName && <div className='invalid-feedback'>{errors.firstName}</div>}
                        </div>

                        <div className='form-group mb-2'>
                            <label className='form-label'>Last Name:</label>
                            <input
                                type = 'test'
                                placeholder='Enter Trainer Last Name'
                                name = 'lastName'
                                value={lastName}
                                className={`form-control ${errors.lastName ? 'is-invalid': ''}`}
                                onChange={handleLastName}
                            >
                            </input>
                            {errors.lastName && <div className='invalid-feedback'>{errors.lastName}</div>}
                        </div>

                        <div className='form-group mb-2'>
                            <label className='form-label'>Specialization:</label>
                            <input
                                type = 'test'
                                placeholder='Enter Trainer Specialization'
                                name = 'specialization'
                                value={specialization}
                                className={`form-control ${errors.specialization ? 'is-invalid': ''}`}
                                onChange={handleSpecialization}
                            >
                            </input>
                            {errors.specialization && <div className='invalid-feedback'>{errors.specialization}</div>}
                        </div>

                        <div className='form-group mb-2'>
                            <label className='form-label'>Repository:</label>
                            <input
                                type = 'test'
                                placeholder='Enter Trainer Repository'
                                name = 'repository'
                                value={repository}
                                className={`form-control ${errors.repository ? 'is-invalid': ''}`}
                                onChange={handleRepository}
                            >
                            </input>
                            {errors.repository && <div className='invalid-feedback'>{errors.repository}</div>}
                        </div>

                        <button className='btn btn-success' onClick={saveOrUpdateTrainer}>Submit</button>
                    </form>
                </div>

            </div>
        </div>

    </div>
  )
}

export default TrainerComponent