function updateRecords(records, id, prop, value) {
    if (value === "") {
      delete records[id][prop];
    } else if (prop !== "tracks") {
      records[id][prop] = value;
    } else {
      records[id][prop] = records[id][prop] || [];
      records[id][prop].push(value);
    }
  
    return records;
  }