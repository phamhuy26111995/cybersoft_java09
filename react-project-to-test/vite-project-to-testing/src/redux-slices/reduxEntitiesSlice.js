import { createSlice, createEntityAdapter, createAsyncThunk } from '@reduxjs/toolkit';

const reduxEntitiesAdapter = createEntityAdapter();

const initialState = reduxEntitiesAdapter.getInitialState({
    userList : []
});

export const fetchUsersRedux = createAsyncThunk("reduxSlice/fetchUsersRedux", async (_, thunkAPI) => {
    const response = await fetch("/fake_data/mini_users.json");
    const data = await response.json();
    thunkAPI.dispatch(addUsers(data));
  });

const reduxEntitiesSlice = createSlice({
  name: 'reduxEntities',
  initialState,
  reducers: {
    addUser: reduxEntitiesAdapter.addOne,
    addUsers: reduxEntitiesAdapter.addMany,
    updateUser: reduxEntitiesAdapter.updateOne,
    removeUser: reduxEntitiesAdapter.removeOne,
    createUser(state, {payload}) {
        const shadow = [...state.userList];
        shadow.push(payload);
        state.userList = shadow;
    }
  },
  
});

export const { addUser, addUsers, updateUser, removeUser, createUser } = reduxEntitiesSlice.actions;
export default reduxEntitiesSlice.reducer;

export const {
  selectById: selectUserById,
  selectIds: selectUserIds,
  selectEntities: selectUserEntities,
  selectAll: selectAllUsers,
  selectTotal: selectTotalUsers
} = reduxEntitiesAdapter.getSelectors(state => state.reduxSlice);
