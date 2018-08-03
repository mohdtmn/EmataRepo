using Emata.Models;
using System;
using System.Collections.Generic;
using System.Data.Entity;
using System.Linq;
using System.Web;

namespace Emata.Context
{
    public class EmataContext : DbContext
    {
        public DbSet<Trash> Trashes { get; set; }
        public DbSet<HeatPoint> HeatPoints { get; set; }
    }
}