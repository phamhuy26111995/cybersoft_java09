import React, { useEffect } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { addUser, createUser, fetchUsersRedux, selectAllUsers, updateUser } from '../../redux-slices/reduxEntitiesSlice';


const Item = React.memo(function Item({name, id}) {

    console.log(`item ${id} render`)

    return (
        <li>{name} {id}</li>
    )
})


function ReduxEntity() {
  const dispatch = useDispatch();
  const users = useSelector(selectAllUsers);


  useEffect(() => {
    dispatch(fetchUsersRedux());
  },[])
  
//   const userList = useSelector(state => state.reduxSlice);

  const handleAddUser = () => {
    dispatch(addUser({ id: getRandomInteger(1, 5), name: `Huy ${getRandomInteger(1,5)}` }));
    // dispatch(createUser({ id: 1, name: 'John Doe' }));
  };

  function getRandomInteger(min, max) {
    return Math.floor(Math.random() * (max - min + 1)) + min;
  }

  function updateUserData() {
    dispatch(updateUser({id : 4 , changes:  {
        name : "Thiên sứ tình yêu"
    }}))
  }




  return (
    <div>
      <button onClick={handleAddUser}>Add User</button>
      <button onClick={updateUserData}>Update User</button>
      <ul>
        {users.map(user => (
          <Item key={user.id} name={user.name} id={user.id} />
        ))}
      </ul>
    </div>
  );
}

export default ReduxEntity;


