import React, {useEffect, useState} from 'react'
import { deleteTrainer, listTrainers } from '../service/TrainerService'
import { useNavigate } from 'react-router-dom'

const ListTrainerComponent = () => {
    
    const [trainers, setTrainers] = useState([])

    const navigator = useNavigate();

    useEffect(() => {
        getAllTrainers();
    }, [])

    function getAllTrainers(){
        listTrainers().then((response) => {
            setTrainers(response.data);
        }).catch(error => {
            console.error(error);
        })
    }

    function addNewTrainer(){
        navigator('/add-trainer')
    }

    function updateTrainer(id){
        navigator(`/edit-trainer/${id}`)
    }

    function removeTrainer(id){
        console.log(id);

        deleteTrainer(id).then((response) =>{
            getAllTrainers();
            console.error(error);
        })
    }

  return (
    <div className='container'>
        <h2 className = 'title'> List of Trainers</h2>
        <button className='btn btn-info mb-20' onClick={addNewTrainer}> Add Trainer</button>
        <table className='table table-striped table-bordered table-dark'>
            <thead>
                <tr>
                    <th>Trainer Id</th>
                    <th>Trainer First Name</th>
                    <th>Trainer Last Name</th>
                    <th>Trainer Specialization</th>
                    <th>Trainer Repository</th>
                    <th>Trainer Login</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                {
                    trainers.map(trainer =>
                        <tr key={trainer.id}>
                            <td>{trainer.id}</td>
                            <td>{trainer.firstName}</td>
                            <td>{trainer.lastName}</td>
                            <td>{trainer.specialization}</td>
                            <td>{trainer.repository}</td>
                            <td>{trainer.login}</td>
                            
                            
                            <td>
                                <button className='btn btn-info' onClick={() => updateTrainer(trainer.id)}>Update</button>
                                <button className='btn btn-warning' onClick={() => removeTrainer(trainer.id)}
                                style={{marginLeft: '20px'}}>Delete</button>
                            </td>
                        </tr>)
                }
            </tbody>
        </table>
    </div>
  )
}

export default ListTrainerComponent