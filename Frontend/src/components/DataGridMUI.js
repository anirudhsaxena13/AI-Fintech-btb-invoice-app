import React, {useState,useEffect} from 'react'
import {DataGrid} from '@mui/x-data-grid'
import { makeStyles } from '@material-ui/core'



const useStyle = makeStyles(()=>({
  iconSeparator:{
    display: "none",
  }
}));

const columns = [
    {field: 'sl_no',headerName: 'Sl No', width: 70},
    {field: 'business_code',headerName: 'Business Code', width: 130},
    {field: 'cust_number',headerName: 'Customer Number', width: 130},
    {field: 'clear_date',headerName: 'Clear Date',width: 130},
    {field: 'doc_id',headerName: 'Document Id',width: 139},
    {field: 'buisness_year',headerName: 'Buisness Year',width: 130},
    {field: 'posting_date',headerName: 'Posting Date', width: 130},
    {field: 'document_create_date',headerName: 'Document Create Date',width: 200},
    {field: 'due_in_date',headerName: 'Due Date',width: 200},
    {field: 'invoice_currency',headerName: 'Invoice Currency',width: 200},
    {field: 'document_type',headerName: 'Document Type', width: 200},
    {field: 'posting_id',headerName: 'Posting Id',width: 200},
    {field: 'total_open_amount',headerName: 'Total Open Amount',width: 200},
    {field: 'baseline_create_date',headerName: 'Baseline Create Date',width: 200},
    {field: 'cust_payment_terms',headerName: 'Customer Payment Terms', width: 200},
    {field: 'invoice_id',headerName: 'Invoice Id',width: 200},
    {field: 'aging_bucket',headerName: 'Aging Bucket',width: 200},
]

export const DataGridMUI = (props) => {
const [pageSize, setPageSize] = useState(5);


const {iconSeparator} = useStyle();


const fetchData = async () => {
  try {
    const res = await fetch("http://localhost:8080/backend/fetching_showing_data");
    const data = await res.json();
    props.setTableData(data);
    console.log(data);
  } catch (error) {
    //console.log(error);
  }
}
useEffect(()=> {
   fetchData();
},[]);



useEffect(()=> {
  fetchData();
  console.log("Data successfully fetched!");
  
},[props.refresh]);

const onRowChecked = (item) => {
  console.log(item);
  props.setCheckedArray(item);
}



const rowData = props.tableData?.map(row => {
  return {
    sl_no: row?.sl_no,
    business_code: row?.business_code,
    cust_number: row?.cust_number,
    clear_date: row?.clear_date,
    doc_id: row?.doc_id,
    buisness_year: row?.buisness_year,
    posting_date: row?.posting_date,
    document_create_date: row?.document_create_date,
    due_in_date: row?.due_in_date,
    invoice_currency: row?.invoice_currency,
    document_type: row?.document_type,
    posting_id: row?.posting_id,
    total_open_amount: row?.total_open_amount,
    baseline_create_date: row?.baseline_create_date,
    cust_payment_terms: row?.cust_payment_terms,
    invoice_id: row?.invoice_id,
    aging_bucket: row?.aging_bucket,
  }
});

  return (
    <div style={{height: 365, width:'100%'}} className="grid">
        <DataGrid sx={[{color: 'whitesmoke', border:"none", '.MuiDataGrid-iconSeparator':{display:"none"}, '.css-i4bv87-MuiSvgIcon-root':{color:"white"}, '.css-11bfvty-MuiToolbar-root-MuiTablePagination-toolbar':{color: "white"}}]} 
            rows={rowData}
            columns={columns}
            
            pageSize={pageSize}
            onPageSizeChange={(newPageSize) => setPageSize(newPageSize)}
            rowsPerPageOptions={[5, 10, 20]}
            pagination
            checkboxSelection 
            getRowId={(row) => row.sl_no}
            className={iconSeparator}
            onSelectionModelChange={onRowChecked}
        />
    </div>
  )
}